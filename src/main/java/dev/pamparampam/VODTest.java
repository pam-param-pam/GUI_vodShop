package dev.pamparampam;

import dev.pamparampam.Films.*;
import dev.pamparampam.utils.GENRE;
import dev.pamparampam.utils.Pricelist;

import static dev.pamparampam.utils.GENRE.*;
import static dev.pamparampam.utils.GoofyBool.*;
import static dev.pamparampam.utils.PaymentType.*;

public class VODTest {

    static int price(Basket basket, GENRE genre) {
        int totalPrice = 0;
        for (ClientProgram clientProgram : basket.getPrograms()) {
            if (clientProgram.getGenre() == genre) {
                totalPrice += clientProgram.getPrice();
            }
        }

        return totalPrice;
    }

    public static void main(String[] args) {

        Pricelist cennik = Pricelist.getPricelist();

        cennik.add(MUSICAL, "Król lew", 5, 3, 12, 6);
        cennik.add(DRAMA, "Król Lear", 2, 15, 10);
        cennik.add(COMEDY, "Królowa", 7, 14);
        cennik.add(COMEDY, "Król", 5, 10);
        cennik.add(ACTION, "Król Artur");

        System.out.println(cennik);
        System.out.println("=============== \n");

        cennik.remove(COMEDY, "Król");
        System.out.println(cennik);

        Client kinoman = new Client("Kinoman", 60, YES);

        kinoman.add(new Drama("Król Lear", 4));
        kinoman.add(new Action("Król Artur", 3));
        kinoman.add(new Musical("Król lew", 2));
        kinoman.add(new Comedy("Korona", 2));

        Wishlist listaKinomana = kinoman.getWishlist();

        System.out.println("Lista życzeń klienta " + listaKinomana);

        Basket koszykKinomana = kinoman.getBasket();
        kinoman.pack();

        System.out.println("Po przepakowaniu, lista życzeń klienta " + kinoman.getWishlist());
        System.out.println("Po przepakowaniu, koszyk klienta " + koszykKinomana);
        System.out.println("Progamy obyczajowe w koszyku klienta Kinoman kosztowały:  " + price(koszykKinomana, COMEDY));
        System.out.println(kinoman.getBasket().getTotalPrice());
        kinoman.pay(CARD, false);

        System.out.println("Po zapłaceniu, klientowi Kinoman zostało: " + kinoman.getWallet() + " zł");
        System.out.println("Po zapłaceniu, koszyk klienta " + kinoman.getBasket());
        System.out.println("Po zapłaceniu, koszyk klienta " + koszykKinomana);

        Client krytyk = new Client("Krytyk", 60, NO);

        krytyk.add(new Musical("Król lew", 2));
        krytyk.add(new Comedy("Królowa", 3));

        System.out.println("Lista życzeń klienta " + krytyk.getWishlist());

        Basket koszykKrytyka = krytyk.getBasket();
        krytyk.pack();

        System.out.println("Po przepakowaniu, lista życzeń klienta " + krytyk.getWishlist());
        System.out.println("Po przepakowaniu, koszyk klienta " + krytyk.getBasket());

        krytyk.pay(TRANSFER, true);

        System.out.println("Po zapłaceniu, klientowi Krytyk zostało: " + krytyk.getWallet() + " zł");

        System.out.println("Po zapłaceniu, koszyk klienta " + koszykKrytyka);

        krytyk.returnVOD(COMEDY, "Królowa", 1);

        System.out.println("Po zwrocie, klientowi krytyk zostało: " + krytyk.getWallet() + " zł");
        System.out.println("Po zwrocie, koszyk klienta " + koszykKrytyka);

    }
}

//wyprowadził na konsolę podobne do poniższych wyniki:
//Lista życzeń klienta Kinoman:
//        Król Lear, typ: obyczaj, ile: 4 urządzenia, cena 10.00
//        Król Artur, typ: sensacja, ile: 3 urządzenia, cena 0.00
//        Król lew, typ: muzyczny, ile: 2 urządzenia, cena 5.00
//Korona, typ: komedia, ile: 2 urządzenia, ceny brak
//
//        Po przepakowaniu, lista życzeń klienta Kinoman:
//Korona, typ: komedia, ile: 2 urządzenia, ceny brak
//
//        Po przepakowaniu, koszyk klienta Kinoman:
//        Król Lear, typ: obyczaj, ile: 4 urządzenia, cena 10.00
//        Król Artur, typ: sensacja, ile: 3 urządzenia, cena 0.00
//        Król lew, typ: muzyczny, ile: 2 urządzenia, cena 5.00
//
//Progamy obyczajowe w koszyku klienta Kinoman kosztowały: 40.00
//
//        Po zapłaceniu, klientowi kinoman zostało: 9.00 zł
//
//        Po zapłaceniu, koszyk klienta Kinoman: -- pusto
//        Po zapłaceniu, koszyk klienta Kinoman: -- pusto
//
//Lista życzeń klienta Krytyk:
//        Król lew, typ: muzyczny, ile: 2 urządzenia, cena 12.00
//Królowa, typ: komedia, ile: 3 urządzenia, cena 14.00
//
//        Po przepakowaniu, lista życzeń klienta Krytyk: -- pusto
//
//        Po przepakowaniu, koszyk klienta Krytyk:
//        Król lew, typ: muzyczny, ile: 2 urządzenia, cena 12.00
//Królowa, typ: komedia, ile: 3 urządzenia, cena 14.00
//
//        Po zapłaceniu, klientowi krytyk zostało: 6.00 zł
//
//        Po zapłaceniu, koszyk klienta krytyk:
//        Król lew, typ: muzyczny, ile: 1 urządzenie, cena 12.00
//
//        Po zwrocie, klientowi krytyk zostało: 20.00 zł
//
//        Po zwrocie, koszyk klienta krytyk:
//        Król lew, typ: muzyczny, ile: 1 urządzenie, cena 12.00
//Królowa, typ: komedia, ile: 1 urządzenie, cena 14.00
//Dodawanie nowych typów programów (podobnych do Drama, Comedy, Musical, ...) musi być zrealizowane w sposób bardzo prosty, polegający na dodawaniu tylko nowej klasy (i ewentualnie powiązanego z klasą interfejsu/enum/...), w takim przypadku modyfikacja pozostałych klas jest niedozwolona.

//Wymagania dodatkowe:
//korzystanie z klas abstrakcyjnych/interfejsów oraz z polimorfizmu jest obowiązkowe
//pożądana jest minimalizacja kodu klas Wishlist oraz Basket
//istnieje tylko jeden obiekt-cennik oraz jeden obiekt-koszyk (na klienta), do których dostęp powinien być realizowany wyłącznie za pomocą metody getPricelist()/getBasket()
//Ważne uwagi:
//Być może są programy niemające ceny w cenniku, wtedy przy przepakowaniu z listy życzeń do koszyka nie są one usuwane z listy życzeń i nie trafią do koszyka.
//Niektóre programy posiadają różne ceny w zależności od liczby urządzeń, na których klienci mogą je wyświetlić.
//Jeśli klient nie ma wystarczającej ilości pieniędzy na całą zawartość koszyka to, zależy od opcji, może zapłacić za część koszyka o ile zapłacona kwota (za dany program) jest wielokrotnością ceny tego programu na 1 urządzeniu (oczywiście zgodnie z cennikiem), w takim przypadku o wyborze programów do zapłacenia zdecyduje aplikacja.
//Przy płaceniu kartą płatniczą klient zapłaci dodatkowo 2% prowizji.
//Przy zwrocie programów z ostatniej transakcji, aplikacja powinna zwrócić klientowi właściwą ilość środków (o ile jest >=0) uwzględniając faktycznie niezwrócone programy (zgodnie z cennikiem). Nie ma zwrotu prowizji.
//Zdefiniowanie odpowiedniej metody toString() w niektórych klasach jest obowiązkowe.
//Aplikacja powinna działać dla dowolnych danych a nie tylko dla tych podanych w powyższym programie. Wszystkie opisane operacje mogą być wykonywane wielokrotnie. Przykładowy program nie uwzględnia wszystkich możliwych sytuacji podanych w opisie zadania.