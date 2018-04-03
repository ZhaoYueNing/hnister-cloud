package cn.zynworld.hnister.common.service;

import cn.zynworld.hnister.common.utils.PageBean;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @auther Buynow Zhao
 * @create 2018/4/3
 *
 * 基于mybatis 的 generator 及 rowbounds分页插件
 * 简化service层的增删改查基础抽象service
 * @param <Type> 实体类
 * @param <PK> 实体类的主键
 * @param <Mapper> 实体类对应的mapper
 * @param <Example> 实体类对应的example
 *
 * 当存在多主键的场景不可调用方法{@link BaseAbstractService::baseDeleteByPrimaryKey} 及 {@link BaseAbstractService::updateByPrimaryKeyMethod}
 * 重载并废弃这两个方法后自实现
 */
public abstract class BaseAbstractService<Type,PK,Mapper,Example>  {
	private final Logger logger = LoggerFactory.getLogger(BaseAbstractService.class);

	@Autowired
	private Mapper mapper;

	private Method insertMethod = null;
	private Method updateByPrimaryKeyMethod = null;
	private Method deleteByExampleMethod = null;
	private Method deleteByPrimaryKeyMethod = null;
	private Method selectByPrimaryKeyMethod = null;
	private Method selectByExampleMethod = null;
	private Method selectByExampleWithRowboundsMethod = null;
	private Method countByExampleMethod = null;

	public Integer baseAdd(Type typeObj){
		if (insertMethod == null) {
			try {
				insertMethod = mapper.getClass().getMethod("insert",new Class[]{typeObj.getClass()});
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		Object result = baseInvocation(insertMethod,new Object[]{typeObj});
		if (result != null) {
			return (Integer) result;
		}
		return 0;
	}

	public Integer baseEdit(Type typeObj){
		if (updateByPrimaryKeyMethod == null) {
			try {
				updateByPrimaryKeyMethod = mapper.getClass().getMethod("updateByPrimaryKey",new Class[]{typeObj.getClass()});
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		Object result = baseInvocation(updateByPrimaryKeyMethod,new Object[]{typeObj});
		if (result != null) {
			return (Integer) result;
		}
		return 0;
	}


	public Integer baseDeleteByExample(Example example){
		if (deleteByExampleMethod == null) {
			try {
				updateByPrimaryKeyMethod = mapper.getClass().getMethod("updateByPrimaryKey",new Class[]{example.getClass()});
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		Object result = baseInvocation(updateByPrimaryKeyMethod,new Object[]{example});
		if (result != null) {
			return (Integer) result;
		}
		return 0;
	}

	public Type baseFindByPrimaryKey(PK pk){
		if (selectByPrimaryKeyMethod == null) {
			try {
				selectByPrimaryKeyMethod = mapper.getClass().getMethod("selectByPrimaryKey",new Class[]{pk.getClass()});
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		Object result = baseInvocation(selectByPrimaryKeyMethod,new Object[]{pk});
		if (result != null) {
			return (Type) result;
		}
		return null;
	}

	public List<Type> baseFindByExample(Example example){
		if (selectByExampleMethod == null) {
			try {
				selectByExampleMethod = mapper.getClass().getMethod("selectByExample",new Class[]{example.getClass()});
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		Object result = baseInvocation(selectByExampleMethod,new Object[]{example});
		if (result != null) {
			return (List<Type>) result;
		}
		return null;
	}

	public Long baseFindCountByExample(Example example) {
		if (countByExampleMethod == null) {
			try {
				countByExampleMethod = mapper.getClass().getMethod("countByExample",new Class[]{example.getClass()});
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		Object result = baseInvocation(countByExampleMethod, new Object[]{example});
		if (result != null) {
			return (Long) result;
		}
		return 0l;
	}

	public PageBean<Type> baseFindByExampleWithPage(Example example, int pageCount, int pageSize){
		if (selectByExampleWithRowboundsMethod == null) {
			try {
				selectByExampleWithRowboundsMethod = mapper.getClass().getMethod("selectByExampleWithRowbounds",new Class[]{example.getClass(),RowBounds.class});
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		Object items = baseInvocation(selectByExampleWithRowboundsMethod,new Object[]{example,new RowBounds(0,pageSize)});
		Object total = baseFindCountByExample(example);
		if (items != null) {
			PageBean<Type> pageBean = new PageBean<Type>();
			pageBean.setItems((List<Type>) items);
			pageBean.setPageCount(pageCount);
			pageBean.setPageSize(pageSize);
			pageBean.setTotal((Long) total);
			return pageBean;
		}
		return null;
	}

	public Integer baseDeleteByPrimaryKey(PK pk){
		if (deleteByPrimaryKeyMethod == null) {
			try {
				deleteByPrimaryKeyMethod = mapper.getClass().getMethod("deleteByPrimaryKey",new Class[]{pk.getClass()});
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		Object result = baseInvocation(deleteByPrimaryKeyMethod,new Object[]{pk});
		if (result != null) {
			return (Integer) result;
		}
		return 0;
	}

	private Class getMapperZlass() {
		return mapper.getClass();
	}

	private Object baseInvocation(Method method,  Object[] params) {
		try {
			Object result = method.invoke(mapper, params);
			return result;
		} catch (IllegalAccessException e) {
			logger.error("BaseAbstractService error：method baseAdd IllegalAccessException");
		} catch (InvocationTargetException e) {
			logger.error("BaseAbstractService error：method baseAdd InvocationTargetException");
		}
		return null;
	}

}
