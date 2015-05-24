package main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class H2DB_Util implements CommandLineRunner{
    //test
    //private Logger log = LoggerFactory.getLogger(CreateH2Database.class);
	private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<String> listPersons() {
        String SQL = "select * from persons";
        return jdbcTemplate.query(SQL, new RowMapper<String>(){
        	@Override
            public String mapRow(ResultSet rs, int rownumber) throws SQLException {
        		return new String(rs.getString(1) + " " + rs.getString(2));
        	}
    	});
     }

	@Override
	public void run(String... arg0) throws Exception {
		log.info("print person names from database:");
		List<String> list = listPersons();
		for(String p : list){
			System.out.println(p);
		}
	}
}