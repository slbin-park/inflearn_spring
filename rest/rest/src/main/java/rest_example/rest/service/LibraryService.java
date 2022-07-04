package rest_example.rest.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import rest_example.rest.model.*;
import rest_example.rest.model.request.AuthorCreationRequest;
import rest_example.rest.model.request.BookCreationRequest;
import rest_example.rest.model.request.BookLendRequest;
import rest_example.rest.model.request.MemberCreationRequest;
import rest_example.rest.repository.AuthorRepository;
import rest_example.rest.repository.BookRepository;
import rest_example.rest.repository.LendRepository;
import rest_example.rest.repository.MemberRepository;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
// lombok이 초기화 되지 않은 필드를 생성함
// 이를 통해 의존성 주입을 할 수 있음
public class LibraryService {

    private final AuthorRepository authorRepository;
    private final MemberRepository memberRepository;
    private final LendRepository lendRepository;
    private final BookRepository bookRepository;

    public Book readBook(Long id){
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()){
            return book.get();
        }

        throw new EntityNotFoundException(
                "책을 찾을수 없습니다."
        );
    }

    public List<Book> readBooks(){
        return bookRepository.findAll();
    }

    public Book readBook(String isbn){
        Optional<Book> book = bookRepository.findByIsbn(isbn);
        if (book.isPresent()){
            return book.get();
        }

        throw new EntityNotFoundException(
                "ISBN 없습니다."
        );
    }

    public  Book createBook(BookCreationRequest book){
        Optional<Author> author = authorRepository.findById(book.getAuthorId());
        if(!author.isPresent()){
            throw new EntityNotFoundException("Author Not Found");
        }

        Book bookToCreate = new Book();
        BeanUtils.copyProperties(book,bookToCreate);
        bookToCreate.setAuthor(author.get());
        return bookRepository.save(bookToCreate);

    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public Member createMembet(MemberCreationRequest request) {
        Member member = new Member();
        BeanUtils.copyProperties(request,member);
        return memberRepository.save(member);
    }

    public Member updateMember(Long id, MemberCreationRequest request) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if (!optionalMember.isPresent()) {
            throw new EntityNotFoundException(
                    "Member not present in th db"
            );
        }

        Member member = optionalMember.get();
        member.setLastName(request.getLastName());
        member.setFirstName(request.getFirstName());
        return memberRepository.save(member);
    }

    public Author createAuthor (AuthorCreationRequest request) {
        Author author = new Author();
        BeanUtils.copyProperties(request, author);
        return authorRepository.save(author);
    }


    public List<String> lendABook (BookLendRequest request) {

        Optional<Member> memberForId = memberRepository.findById(request.getMemberId());
        if (!memberForId.isPresent()) {
            throw new EntityNotFoundException("Member not present in the database");
        }

        Member member = memberForId.get();
        if (member.getStatus() != MemberStatus.ACTIVE) {
            throw new RuntimeException("User is not active to proceed a lending.");
        }

        List<String> booksApprovedToBurrow = new ArrayList<>();

        request.getBookIds().forEach(bookId -> {

            Optional<Book> bookForId = bookRepository.findById(bookId);
            if (!bookForId.isPresent()) {
                throw new EntityNotFoundException("Cant find any book under given ID");
            }

            Optional<Lend> burrowedBook = lendRepository.findByBookAndStatus(bookForId.get(), LendStatus.BURROWED);
            if (!burrowedBook.isPresent()) {
                booksApprovedToBurrow.add(bookForId.get().getName());
                Lend lend = new Lend();
                lend.setMember(memberForId.get());
                lend.setBook(bookForId.get());
                lend.setStatus(LendStatus.BURROWED);
                lend.setStartOn(Instant.now());
                lend.setDueOn(Instant.now().plus(30, ChronoUnit.DAYS));
                lendRepository.save(lend);
            }

        });
        return booksApprovedToBurrow;
    }
}
