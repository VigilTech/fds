package fds.space;

/**
 * Created by nigel on 01/12/2015.
 */
import com.zink.fly.Fly;
import com.zink.fly.kit.FlyFactory;

public class SpaceClient {

    public static class Movie {

        public String title;
        public Integer stars;
        public String rating;
        public String narrative;

        public Movie(String title, Integer stars, String rating, String narrative) {
            super();
            this.title = title;
            this.stars = stars;
            this.rating = rating;
            this.narrative = narrative;
        }

        public Movie(String title, int stars, String rating, String narrative) {
            this(title, new Integer(stars), rating, narrative);
        }
    }


    public static void main(String[] args) {

        Fly fly = FlyFactory.makeFly("192.168.99.100");

        Movie movie = new Movie("Rio 2", new Integer(5), "U", "Nice Birds fight bad people");
        final int lease = 60 * 60 * 1000;  // 60 * 60 Seconds => 60 minutes => 1 hour
        fly.write(movie, lease);
        // TODO : Write more than movie to the space

        Movie template = new Movie(null, null, null, null);
        // TODO : Set up a templates to read various movies

        Movie result = fly.read(template, 100);
        System.out.println(result.title);
    }

}