package com.lld.imdb;

import com.lld.imdb.constant.*;
import com.lld.imdb.exception.ShowException;
import com.lld.imdb.model.*;
import com.lld.imdb.repository.ShowRepository;
import com.lld.imdb.repository.impl.ShowRepositoryImpl;
import com.lld.imdb.service.FAQService;
import com.lld.imdb.service.ReviewService;
import com.lld.imdb.service.ShowService;
import com.lld.imdb.service.impl.FAQServiceImpl;
import com.lld.imdb.service.impl.ReviewServiceImpl;
import com.lld.imdb.service.impl.ShowServiceImpl;

import java.util.Arrays;
import java.util.List;

public class MainApplication {
    private static void testConstructMovie() {
        System.out.println("Starting test testConstructMovie");
        System.out.println("---------------------------------");

        Show show = new Show(ShowType.MOVIE);
        ShowStaticDetails staticDetails = new ShowStaticDetails();
        staticDetails.setTitle("The Shawshank Redemption");
        staticDetails.setDescription("Over the course of several years, two " +
                "convicts form a friendship, seeking consolation and, eventually, " +
                "redemption through basic compassion.");
        staticDetails.setGenres(Arrays.asList(Genre.DRAMA));
        staticDetails.setLanguages(Arrays.asList(Language.ENGLISH));
        staticDetails.setPlot("Chronicles the experiences of a formerly successful " +
                "banker as a prisoner ...");
        staticDetails.setAspectRatio("1.85 : 1");
        staticDetails.setOriginCountry("United States");
        staticDetails.setReleaseDate("February 17, 1995 (United Kingdom)");
        staticDetails.setRuntime(144);
        staticDetails.setSoundMix("Dolby Digital");
        staticDetails.setTags(Arrays.asList("drama", "top 10"));
        staticDetails.setProductionCompanies(Arrays.asList("Castle Rock Entertainment"));

        Persona p1 = new Persona("Tim Robbins", Gender.MALE);
        p1.setType(Arrays.asList(PersonaType.ACTOR));
        PersonaDescription p1Desc = new PersonaDescription(p1);
        p1Desc.setDescription("Andy Dufresne");

        Persona p2 = new Persona("Morgan Freeman", Gender.MALE);
        p2.setType(Arrays.asList(PersonaType.ACTOR));
        PersonaDescription p2Desc = new PersonaDescription(p2);
        p2Desc.setDescription("Ellis Boyd 'Red' Redding");

        CastAndCrew castAndCrew = new CastAndCrew();
        castAndCrew.setTitle("Top Cast");
        castAndCrew.setPersonaDescriptions(Arrays.asList(p1Desc, p2Desc));

        staticDetails.setCastAndCrewList(Arrays.asList(castAndCrew));
        show.setStaticDetails(staticDetails);

        ShowRepository showRepository = new ShowRepositoryImpl();
        ShowService showService = new ShowServiceImpl(showRepository);
        showService.addShow(show);

        List<Show> retrievedShows = showService.searchShows(show.getStaticDetails().getTitle());
        System.out.println(Arrays.deepToString(retrievedShows.toArray()));

        ReviewService reviewService = new ReviewServiceImpl(showRepository);

        User user = new User("user_123");
        UserReview review1 = new UserReview();
        review1.setRating(8);
        review1.setTitle("Absolutely fantastic movie");
        review1.setDescription("blah blah ...");
        review1.setDate(System.currentTimeMillis());
        review1.setUser(user);

        UserReview review2 = new UserReview();
        review2.setRating(6);
        review2.setTitle("Absolutely fantastic movie");
        review2.setDescription("blah blah ...");
        review2.setDate(System.currentTimeMillis());
        review2.setUser(user);

        reviewService.addReview(show, review1);
        reviewService.addReview(show, review2);

        retrievedShows = showService.searchShows(show.getStaticDetails().getTitle());
        System.out.println("Current Rating: " + retrievedShows.get(0).getDynamicDetails().getRating());

        FAQService faqService = new FAQServiceImpl(showRepository);
        FAQ faq = new FAQ();
        faq.setQuestion("Why is Captain Hadley so cruel?");
        faq.setAnswer("Hadley was the captain of the prison guard ...");
        show = faqService.addFAQ(show, faq);
        System.out.println(Arrays.asList(show.getDynamicDetails().getFaqs().toArray()));
    }

    private static void testConstructTVShow() throws ShowException {
        System.out.println();
        System.out.println("Starting test testConstructTVShow");
        System.out.println("----------------------------------");

        Show show = new Show(ShowType.TV_SERIES);
        ShowStaticDetails staticDetails = new ShowStaticDetails();
        staticDetails.setTitle("Breaking Bad");
        staticDetails.setDescription("A chemistry teacher diagnosed with inoperable " +
                "lung cancer turns to manufacturing and selling methamphetamine with " +
                "a former student in order to secure his family's future.");
        staticDetails.setGenres(Arrays.asList(Genre.CRIME, Genre.DRAMA, Genre.THRILLER));
        staticDetails.setLanguages(Arrays.asList(Language.ENGLISH, Language.SPANISH));
        staticDetails.setPlot("Walter White is a chemistry genius but works as a chemistry " +
                "teacher at a high school ...");
        staticDetails.setAspectRatio("1.85 : 1");
        staticDetails.setOriginCountry("United States");
        staticDetails.setReleaseDate("September 28, 2008 (United Kingdom)");
        staticDetails.setRuntime(45);
        staticDetails.setSoundMix("Dolby Digital");
        staticDetails.setTags(Arrays.asList("crime", "top 10"));
        staticDetails.setProductionCompanies(Arrays.asList("High Bridge Productions",
                "Gran Via Productions", "Sony Pictures Television"));

        Persona p1 = new Persona("Bryan Cranston", Gender.MALE);
        p1.setType(Arrays.asList(PersonaType.ACTOR));
        PersonaDescription p1Desc = new PersonaDescription(p1);
        p1Desc.setDescription("Walter White");

        Persona p2 = new Persona("Betsy Brandt", Gender.FEMALE);
        p2.setType(Arrays.asList(PersonaType.ACTOR));
        PersonaDescription p2Desc = new PersonaDescription(p2);
        p2Desc.setDescription("Marie Schrader");

        CastAndCrew castAndCrew = new CastAndCrew();
        castAndCrew.setTitle("Top Cast");
        castAndCrew.setPersonaDescriptions(Arrays.asList(p1Desc, p2Desc));

        staticDetails.setCastAndCrewList(Arrays.asList(castAndCrew));
        show.setStaticDetails(staticDetails);

        Show episode = new Show(ShowType.TV_EPISODE);
        ShowStaticDetails staticEpisodeDetails = new ShowStaticDetails();
        staticEpisodeDetails.setTitle("S1 - E1, Pilot");
        staticEpisodeDetails.setDescription("Diagnosed with terminal lung cancer, chemistry " +
                "teacher Walter White teams up with former student Jesse Pinkman " +
                "to cook and sell crystal meth.");
        staticEpisodeDetails.setGenres(Arrays.asList(Genre.CRIME, Genre.DRAMA, Genre.THRILLER));
        staticEpisodeDetails.setLanguages(Arrays.asList(Language.ENGLISH, Language.SPANISH));
        staticEpisodeDetails.setPlot("Days after his 50th birthday, chemistry teacher Walter " +
                "White's life ...");
        staticEpisodeDetails.setAspectRatio("1.85 : 1");
        staticEpisodeDetails.setOriginCountry("United States");
        staticEpisodeDetails.setReleaseDate("September 28, 2008 (United Kingdom)");
        staticEpisodeDetails.setRuntime(58);
        staticEpisodeDetails.setSoundMix("Dolby Digital");
        staticEpisodeDetails.setTags(Arrays.asList("crime", "top 10"));
        staticEpisodeDetails.setProductionCompanies(Arrays.asList("High Bridge Productions",
                "Gran Via Productions", "Sony Pictures Television"));

        Persona p3 = new Persona("Vince Gilligan", Gender.MALE);
        p3.setType(Arrays.asList(PersonaType.DIRECTOR));
        PersonaDescription p3Desc = new PersonaDescription(p3);
        p3Desc.setDescription("Director");

        Persona p4 = new Persona("Melissa Bernstein", Gender.FEMALE);
        p4.setType(Arrays.asList(PersonaType.PRODUCER));
        PersonaDescription p4Desc = new PersonaDescription(p4);
        p4Desc.setDescription("Marie Schrader");

        CastAndCrew crew = new CastAndCrew();
        castAndCrew.setTitle("Important Crew");
        castAndCrew.setPersonaDescriptions(Arrays.asList(p3Desc, p4Desc));

        staticEpisodeDetails.setCastAndCrewList(Arrays.asList(crew));
        episode.setStaticDetails(staticEpisodeDetails);

        ShowRepository showRepository = new ShowRepositoryImpl();
        ShowService showService = new ShowServiceImpl(showRepository);
        showService.addShow(show);
        Show retrievedShow = showService.addEpisode(show, episode);
        System.out.println("Episode count = " + retrievedShow.getEpisodes().size());
        System.out.println("Episode name = " + showService.searchShows(
                retrievedShow.getStaticDetails().getTitle()).get(0).getEpisodes()
                .get(0).getStaticDetails().getTitle());
    }

    public static void main(String[] args) throws ShowException {
        testConstructMovie();
        testConstructTVShow();
    }
}
