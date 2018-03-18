import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
public class ScheduleFileReaderTest {

    @Test
    public void readFromFile() throws Exception {
        File file = new File(ScheduleFileReaderTest.class.getClassLoader()
                .getResource("schedule.xml").getFile());
        List<Talk> talks = ScheduleFileReader.readFromFile(file);

        assertEquals(3, talks.size());
    }
}