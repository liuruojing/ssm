package cn.jarvan.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringForDate implements Converter<String, Date> {
	public Date convert(String source) {
		// TODO Auto-generated method stub
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return simple.parse(source);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
