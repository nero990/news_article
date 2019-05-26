package article.news.util;

/**
 * String helper methods
 *
 * @author Nero Okiewhru
 * @since 2019-05-25
 */
public class StringUtils {

    public static String generateSlug(String title) {
        return generateSlug(title, null);
    }

    public static String generateSlug(String title, Integer length) {
        return generateSlug(title, "-", length);
    }

    public static String generateSlug(String title, String delimiter, Integer length) {
        if (title == null) return "";

        title = title.replaceAll("[^A-Za-z0-9]", " ") // remove special characters
                .replaceAll("\\s+", " "); // remove extra spaces
        String slug = String.join(delimiter, title.split(" ")).toLowerCase();
        slug = (length != null) ? slug.substring(0, length) : slug;
        return slug;
    }
}