package cn.zynworld.hnister.hnisternewsservice.dao;

import cn.zynworld.hnister.common.domain.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import cn.zynworld.hnister.common.utils.PageBean;

import java.sql.*;
import java.util.List;

/**
 * @auther Buynow Zhao
 * @create 2018/1/2
 */
@Repository
public class NewsDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
//	插入数据
	public boolean add(News news) {
		int updateCounter = jdbcTemplate.update("INSERT INTO t_news(title,content,author,post_date,module_id) VALUES (?,?,?,?,?)", new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, news.getTitle());
				ps.setString(2, news.getContent());
				ps.setString(3, news.getAuthor());
				ps.setDate(4, new Date(news.getPostDate().getTime()));
				if (news.getModuleId() != null){
					ps.setInt(5, news.getModuleId());
				} else {
					ps.setNull(5, Types.INTEGER);
				}
			}
		});
		if (updateCounter >= 0) {
			return true;
		}
		return false;
	}
	public List<News> findAll(){
		List<News> newsList = jdbcTemplate.query("SELECT * FROM t_news", new RowMapper<News>() {
			@Override
			public News mapRow(ResultSet resultSet, int i) throws SQLException {
				News news = new News();
				news.setId(resultSet.getLong("id"));
				news.setTitle(resultSet.getString("title"));
				news.setAuthor(resultSet.getString("author"));
				news.setContent(resultSet.getString("content"));
				news.setPostDate(resultSet.getDate("post_date"));
				news.setModuleId(resultSet.getInt("module_id"));
				return news;
			}
		});
		return newsList;
	}

	public boolean deleteByModuleId(Integer moduleId){
		int result = jdbcTemplate.update("DELETE FROM t_news WHERE module_id = ?", new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1,moduleId);
			}
		});
		return result > 0;
	}
	public boolean deleteById(Long id){
		int result = jdbcTemplate.update("DELETE FROM t_news WHERE id = ?", new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setLong(1,id);
			}
		});
		return result > 0;
	}
	public boolean updateByModuleIdSetNull(Integer moduleId){
		int result = jdbcTemplate.update("UPDATE t_news SET module_id = NULL WHERE module_id = ?", new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1,moduleId);
			}
		});
		return result > 0;
	}

	/**
	 *
	 * @param pageCount
	 * @param pageSize
	 * @param moduleId 为0查询null 为-1 查询所有
	 * @return
	 */
	public PageBean<News> findByPage(Integer pageCount,Integer pageSize,Integer moduleId) {
		PageBean<News> pageBean = new PageBean<>();
		pageBean.setPageCount(pageCount).setPageSize(pageSize);
		String sql = "";
		PreparedStatementSetter ps = null;
		if (moduleId == -1){
//		查詢所有
			sql = "SELECT SQL_CALC_FOUND_ROWS * FROM t_news LIMIT ?,? ";
			ps = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1,pageBean.getFirstItemIndex());
					ps.setInt(2,pageSize);
				}
			};
		} else if (moduleId == 0){
//		查詢空
			sql = "SELECT SQL_CALC_FOUND_ROWS * FROM t_news WHERE ISNULL(module_id)  LIMIT ?,? ";
			ps = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1,pageBean.getFirstItemIndex());
					ps.setInt(2,pageSize);
				}
			};
		}else {
			sql = "SELECT SQL_CALC_FOUND_ROWS * FROM t_news WHERE module_id = ? LIMIT ?,? ";
			ps = new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1,moduleId);
					ps.setInt(2,pageBean.getFirstItemIndex());
					ps.setInt(3,pageSize);
				}
			};
		}
		List<News> queryResult = jdbcTemplate.query(sql, ps , new RowMapper<News>() {
			@Override
			public News mapRow(ResultSet resultSet, int i) throws SQLException {
				News news = new News();
				news.setId(resultSet.getLong("id"))
						.setTitle(resultSet.getString("title"))
						.setAuthor(resultSet.getString("author"))
						.setContent(resultSet.getString("content"))
						.setPostDate(resultSet.getDate("post_date"))
						.setModuleId(resultSet.getInt("module_id"));
				return news;
			}
		});
		Long total = jdbcTemplate.queryForObject("SELECT FOUND_ROWS()", new RowMapper<Long>() {
			@Override
			public Long mapRow(ResultSet resultSet, int i) throws SQLException {
				return resultSet.getLong(1);
			}
		});
		pageBean.setItems(queryResult).setTotal(total);
		return pageBean;
	}

	public boolean update(News news){
		int result = jdbcTemplate.update("UPDATE t_news SET title=?,content=?,author=?,post_date=?,module_id=? WHERE id=?", new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, news.getTitle());
				ps.setString(2, news.getContent());
				ps.setString(3, news.getAuthor());
				ps.setDate(4, new Date(news.getPostDate().getTime()));
				ps.setInt(5, news.getModuleId());
				ps.setLong(6, news.getId());
			}
		});
		return result > 0;
	}
}
