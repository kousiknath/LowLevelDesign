package com.lld.invertedindex;

import com.lld.invertedindex.analyzer.TermAnalyzer;
import com.lld.invertedindex.document.Document;
import com.lld.invertedindex.index.DocumentIdRangeBasedIndex;
import com.lld.invertedindex.index.Index;
import com.lld.invertedindex.index.IndexingMetadata;
import com.lld.invertedindex.index.Match;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {

    private static void test1() {
        IndexingMetadata metadata = new IndexingMetadata(100, 10);

        Index index = new DocumentIdRangeBasedIndex(metadata);
        List<Document> documents = new ArrayList<>();
        for (int i = 0; i < metadata.getTotalDocuments(); i++) {
            int words = 10 + new Random().nextInt(5);
            StringBuilder builder = new StringBuilder();

            for (int w = 0; w < words; w++) {
                builder.append(getTestData().get(new Random().nextInt(100)));
                builder.append(" ");
            }

            documents.add(new Document(i, builder.toString().trim()));
        }

        index.index(documents, new TermAnalyzer());
        Match match = index.query(List.of("Huckleberry"));
        System.out.println(match.toString());
    }

    private static List<String> getTestData() {
        return Arrays.asList(
                "Apple", "Banana", "Cherry", "Date", "Elderberry", "Fig", "Grape", "Honeydew", "Indian Fig", "Jackfruit",
                "Kiwi", "Lemon", "Mango", "Nectarine", "Orange", "Papaya", "Quince", "Raspberry", "Strawberry", "Tangerine",
                "Ugli Fruit", "Vanilla Bean", "Watermelon", "Xigua", "Yellow Passion Fruit", "Zucchini", "Apricot", "Blackberry", "Cantaloupe", "Dragonfruit",
                "Eggfruit", "Feijoa", "Gooseberry", "Huckleberry", "Illawarra Plum", "Jujube", "Kumquat", "Lime", "Mulberry", "Nance",
                "Olive", "Pineapple", "Quararibea", "Red Currant", "Soursop", "Tomato", "Uva", "Voavanga", "Wolfberry", "Ximenia",
                "Yamamomo", "Ziziphus", "Avocado", "Blueberry", "Clementine", "Durian", "Elderflower", "Finger Lime", "Gac", "Hackberry",
                "Imbe", "Jabuticaba", "Key Lime", "Loganberry", "Mandarin", "Nutmeg", "Ogen Melon", "Peach", "Queen Anne Cherry", "Rambutan",
                "Sapodilla", "Tamarind", "Umbu", "Velvet Apple", "White Currant", "Yumberry", "Zabergau", "Ackee", "Barbados Cherry", "Cherimoya",
                "Dewberry", "Entawak", "Forest Strawberry", "Grapefruit", "Hog Plum", "Ilama", "Jocote", "Kiwano", "Lychee", "Marionberry",
                "Noni", "Ohelo Berry", "Persimmon", "Quandong", "Rose Apple", "Salak", "Tayberry", "Ugni", "Voavanga", "White Sapote"
        );
    }

    public static void main(String[] args) {
        test1();
    }
}
