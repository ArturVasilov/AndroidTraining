package ru.samples.itis.githubclient.content.tables;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import ru.samples.itis.githubclient.content.Repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Artur Vasilov
 */
@RunWith(JUnit4.class)
public class RepositoryTableTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testNotNullInstance() throws Exception {
        assertNotNull(RepositoryTable.TABLE);
    }

    @Test
    public void testOnCreate() throws Exception {
        SQLiteDatabase database = mock(SQLiteDatabase.class);
        String sql = "CREATE TABLE IF NOT EXISTS RepositoryTable" +
                "(stars INTEGER, name VARCHAR(255), description VARCHAR(255), language VARCHAR(255), " +
                "forks INTEGER, watchers INTEGER);";

        doAnswer(invocation -> {
            assertEquals(sql, invocation.getArguments()[0].toString());
            return null;
        }).when(database).execSQL(anyString());

        RepositoryTable.TABLE.onCreate(database);
    }

    @Test
    public void testLastUpgradeVersion() throws Exception {
        assertEquals(1, RepositoryTable.TABLE.getLastUpgradeVersion());
    }

    @Test
    public void testFromCursor() throws Exception {
        Cursor cursor = mock(Cursor.class);

        when(cursor.getColumnIndex(anyString())).thenAnswer(invocation -> {
            String arg = (String) invocation.getArguments()[0];
            switch (arg) {
                case "stars": return 0;
                case "name": return 1;
                case "description": return 2;
                case "language": return 3;
                case "forks": return 4;
                case "watchers": return 5;
                default: throw new IllegalArgumentException("Irregular column");
            }
        });

        when(cursor.getString(anyInt())).thenAnswer(invocation -> {
            int index = (int) invocation.getArguments()[0];
            switch (index) {
                case 1: return "AndroidTraining";
                case 2: return "Test Android repo";
                case 3: return "Java";
                default: throw new IllegalArgumentException("Irregular column index");
            }
        });

        when(cursor.getInt(anyInt())).thenAnswer(invocation -> {
            int index = (int) invocation.getArguments()[0];
            switch (index) {
                case 0: return 20;
                case 4: return 3;
                case 5: return 40;
                default: throw new IllegalArgumentException("Irregular column index");
            }
        });

        Repository repository = RepositoryTable.TABLE.fromCursor(cursor);
        Assert.assertEquals("AndroidTraining", repository.getName());
        Assert.assertEquals("Test Android repo", repository.getDescription());
        Assert.assertEquals("Java", repository.getLanguage());
        Assert.assertEquals(20, repository.getStarsCount());
        Assert.assertEquals(3, repository.getForksCount());
        Assert.assertEquals(40, repository.getWatchersCount());
    }
}
