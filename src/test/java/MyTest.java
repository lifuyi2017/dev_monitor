import com.lifuyi.dev_monitor.model.mongo.current_data.DevicePredicData;
import com.lifuyi.dev_monitor.mongodao.DevicePredicDataDao;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class MyTest {

    @Resource
    private DevicePredicDataDao devicePredicDataDao;

    @Test
    public void  test(){
        DevicePredicData devHealthyByDevId = devicePredicDataDao.getDevHealthyByDevId("32");
        System.out.println(devHealthyByDevId.getCompany_id());
    }
}
