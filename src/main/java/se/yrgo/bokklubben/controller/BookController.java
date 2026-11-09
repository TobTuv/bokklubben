package se.yrgo.bokklubben.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import java.time.LocalDate;
import se.yrgo.bokklubben.domain.Book;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.stream.Collectors;

@Controller
public class BookController {

    @GetMapping("/home")
    public String home(Model model) {

        model.addAttribute("greeting", "Välkommen till Bokklubben!");
        model.addAttribute("date", LocalDate.now());

        return "home";
    }

    @GetMapping("/books")
    public String books(Model model) {

        List<Book> books = List.of(
                new Book("Harry Potter", "J.K. Rowling", "Fantasy"),
                new Book("Män som hatar kvinnor", "Stieg Larsson", "Deckare"),
                new Book("Dune", "Frank Herbert", "Science Fiction"),
                new Book("Stolthet och fördom", "Jane Austen", "Romantik")
        );

        model.addAttribute("books", books);

        return "booklist";
    }

    @GetMapping("/genre")
    public String genre(
            @RequestParam(required = false) String type,
            Model model) {

        List<Book> books = List.of(
                new Book("Harry Potter", "J.K. Rowling", "Fantasy"),
                new Book("Män som hatar kvinnor", "Stieg Larsson", "Deckare"),
                new Book("Dune", "Frank Herbert", "Science Fiction"),
                new Book("Stolthet och fördom", "Jane Austen", "Romantik")
        );

        List<Book> filteredBooks;

        if (type == null || type.isEmpty()) {
            filteredBooks = books;
        } else {
            filteredBooks = books.stream()
                    .filter(book -> book.getGenre().equalsIgnoreCase(type))
                    .collect(Collectors.toList());
        }

        model.addAttribute("books", filteredBooks);
        model.addAttribute("type", type);

        return "genre";
    }
}
