package ru.samples.itis.githubclient.content;

import com.google.gson.reflect.TypeToken;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ru.samples.itis.githubclient.TestUtils;
import rx.Observable;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

/**
 * @author Artur Vasilov
 */
@RunWith(JUnit4.class)
public class RepositoryTest {

    private static final String REPOSITORY_SINGLE = "/response/repository/repository_single.json";
    private static final String REPOSITORY_LIST = "/response/repository/repository_list.json";

    @Test
    public void testConstructor() throws Exception {
        Repository repository = new Repository();
        assertNotNull(repository);
    }

    @Test
    public void testFields() throws Exception {
        Repository repository = new Repository();

        assertNull(repository.getName());
        assertNull(repository.getDescription());
        assertNull(repository.getLanguage());
        assertEquals(0, repository.getStarsCount());
        assertEquals(0, repository.getForksCount());
        assertEquals(0, repository.getWatchersCount());

        repository.setName("AndroidTraining");
        repository.setDescription("Test Android repo");
        repository.setLanguage("Java");
        repository.setStarsCount(20);
        repository.setForksCount(3);
        repository.setWatchersCount(40);

        assertEquals("AndroidTraining", repository.getName());
        assertEquals("Test Android repo", repository.getDescription());
        assertEquals("Java", repository.getLanguage());
        assertEquals(20, repository.getStarsCount());
        assertEquals(3, repository.getForksCount());
        assertEquals(40, repository.getWatchersCount());
    }

    @Test
    public void testNullName() throws Exception {
        Repository repository = new Repository();
        repository.setName(null);
        assertEquals("", repository.getName());
    }

    @Test
    public void testNullDescription() throws Exception {
        Repository repository = new Repository();
        repository.setDescription(null);
        assertEquals("", repository.getDescription());
    }

    @Test
    public void testNullLanguage() throws Exception {
        Repository repository = new Repository();
        repository.setLanguage(null);
        assertEquals("", repository.getLanguage());
    }

    @Test
    public void testNegativeStars() throws Exception {
        Repository repository = new Repository();
        repository.setStarsCount(-5);
        assertEquals(0, repository.getStarsCount());

        repository.setStarsCount(-100000);
        assertEquals(0, repository.getStarsCount());
    }

    @Test
    public void testNegativeForks() throws Exception {
        Repository repository = new Repository();
        repository.setForksCount(-93);
        assertEquals(0, repository.getForksCount());

        repository.setForksCount(-1);
        assertEquals(0, repository.getForksCount());
    }

    @Test
    public void testNegativeWatchers() throws Exception {
        Repository repository = new Repository();
        repository.setWatchersCount(-10);
        assertEquals(0, repository.getWatchersCount());

        repository.setWatchersCount(-1);
        assertEquals(0, repository.getWatchersCount());
    }

    @Test
    public void testFromGson() throws Exception {
        String gson = TestUtils.readFile(REPOSITORY_SINGLE);
        Repository repository = GsonHolder.getGson().fromJson(gson, Repository.class);

        assertEquals("Hello-World", repository.getName());
        assertEquals("This your first repo!", repository.getDescription());
        assertEquals("Java", repository.getLanguage());
        assertEquals(80, repository.getStarsCount());
        assertEquals(9, repository.getForksCount());
        assertEquals(80, repository.getWatchersCount());
    }

    @Test
    public void testGsonList() throws Exception {
        String gson = TestUtils.readFile(REPOSITORY_LIST);
        Type listType = new TypeToken<ArrayList<Repository>>() {
        }.getType();

        List<Repository> repositoryList = GsonHolder.getGson().fromJson(gson, listType);
        assertEquals(2, repositoryList.size());

        Repository repository = repositoryList.get(1);
        assertEquals("Hello-World2", repository.getName());
        assertEquals("Hehe", repository.getDescription());
        assertEquals("PHP", repository.getLanguage());
        assertEquals(15, repository.getStarsCount());
        assertEquals(4, repository.getForksCount());
        assertEquals(20, repository.getWatchersCount());
    }

    @Test
    public void testObservableJson() throws Exception {
        String gson = TestUtils.readFile(REPOSITORY_LIST);
        Type listType = new TypeToken<ArrayList<Repository>>() {
        }.getType();

        List<Repository> repositoryList = GsonHolder.getGson().fromJson(gson, listType);
        Observable.from(repositoryList).toList()
                .subscribe(repositories -> assertEquals(2, repositories.size()));
    }
}
