package lv.ctco.cukesrest.internal.matchers;

import lv.ctco.cukesrest.internal.helpers.*;
import org.hamcrest.*;
import org.hamcrest.Matcher;

import java.util.regex.*;

public class ContainsPattern extends BaseMatcher<CharSequence> {

    private final Pattern p;
    private final boolean match;

    public ContainsPattern(Pattern p, boolean match) {
        this.p = p;
        this.match = match;
    }

    public ContainsPattern(Pattern p) {
        this(p, false);
    }

    public ContainsPattern(String regex, boolean match) {
        this(Pattern.compile(Strings.escapeRegex(regex)), match);
    }

    public ContainsPattern(String regex) {
        this(Pattern.compile(regex));
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a string ")
          .appendText(match ? "matching" : "containing")
          .appendText(" /")
          .appendText(p.pattern())
          .appendText("/");
    }

    public static Matcher<CharSequence> containsPattern(String regex) {
        return new ContainsPattern(regex, false);
    }

    public static Matcher<CharSequence> containsPattern(Pattern p) {
        return new ContainsPattern(p, false);
    }

    public static Matcher<CharSequence> matchesPattern(String regex) {
        return new ContainsPattern(regex, true);
    }

    public static Matcher<CharSequence> matchesPattern(Pattern p) {
        return new ContainsPattern(p, true);
    }

    @Override
    public boolean matches(Object o) {
        boolean isString = o instanceof String;
        CharSequence item = (isString) ? (CharSequence) o : String.valueOf(o);
        if (match) {
            if (p.matcher(item).matches()) return true;
        } else {
            if (p.matcher(item).find()) return true;
        }
        return false;
    }
}
