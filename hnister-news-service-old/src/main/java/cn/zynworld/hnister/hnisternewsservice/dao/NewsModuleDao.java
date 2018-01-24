package cn.zynworld.hnister.hnisternewsservice.dao;

import cn.zynworld.hnister.common.domain.NewsModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @auther Buynow Zhao
 * @create 2018/1/7
 */
@Repository
public class NewsModuleDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;


	public List<NewsModule> findAll(){
		List<NewsModule> newsModules = jdbcTemplate.query("SELECT * FROM t_news_module", new RowMapper<NewsModule>() {
			@Override
			public NewsModule mapRow(ResultSet resultSet, int i) throws SQLException {
				NewsModule newsModule = new NewsModule();
				newsModule.setId(resultSet.getInt("id"));
				newsModule.setName(resultSet.getString("name"));
				newsModule.setRemark(resultSet.getString("remark"));
				newsModule.setNumber(resultSet.getLong("number"));
				return newsModule;
			}
		});
		return newsModules;
	}

	public boolean add(NewsModule newsModule){
		int result = jdbcTemplate.update("INSERT INTO t_news_module(name,remark) VALUES (?,?)", new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, newsModule.getName());
				ps.setString(2,newsModule.getRemark());
			}
		});
		return result > 0;
	}


	public boolean deleteById(Integer id){
		int  result = jdbcTemplate.update("DELETE FROM t_news_module WHERE id = ?", new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1,id);
			}
		});
		return result > 0;
	}

	public boolean edit(NewsModule newsModule) {
		int result = jdbcTemplate.update("UPDATE t_news_module SET name = ?, remark = ? WHERE id = ?", new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1,newsModule.getName());
				ps.setString(2,newsModule.getRemark());
				ps.setInt(3,newsModule.getId());
			}
		});
		return result > 0;
	}

	/**
	 * 改变文章数量
	 * @param changeVal 对文章数量进行 number = number + changeVal
	 * @return
	 */
	public boolean editNumber(Integer id,Integer changeVal){
		int result = jdbcTemplate.update("UPDATE t_news_module AS m SET m.`number` = (m.`number`+ (?)) WHERE id = ?;", new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1,changeVal);
				ps.setInt(2,id);
			}
		});

		return result > 0;
	}
}
