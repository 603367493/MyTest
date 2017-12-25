package grad;

import java.util.Properties;

import com.cm.common.PropertiesUtils;

public class MainTest {

	public static void main(String[] args) {
		PropertiesUtils.getValue("/config/jdbc.properties", "username");
	}
}
