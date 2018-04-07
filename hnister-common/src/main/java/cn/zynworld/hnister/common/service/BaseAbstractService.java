package cn.zynworld.hnister.common.service;

import cn.zynworld.hnister.common.utils.PageBean;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
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
 * 当存在多主键的场景不可调用方法{@link BaseAbstractService::baseDeleteByPrimaryKey}
 * 重载并废弃这个方法后自实现
 */
public abstract class BaseAbstractService<Type,PK,Mapper,Example>  {
	private final Logger logger = LoggerFactory.getLogger(BaseAbstractService.class);

	@Autowired
	private Mapper mapper;

	//获取泛型class信息
	private Class<Example> typeClass =  (Class <Example>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	private Class<Example> pkClass =  (Class <Example>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	private Class<Example> exampleClass =  (Class <Example>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[3];

	private Method insertMethod = null;
	private Method updateByPrimaryKeyMethod = null;
	private Method deleteByExampleMethod = null;
	private Method deleteByPrimaryKeyMethod = null;
	private Method selectByPrimaryKeyMethod = null;
	private Method selectByExampleMethod = null;
	private Method selectByExampleWithRowboundsMethod = null;
	private Method countByExampleMethod = null;

	@Transactional
	public Integer baseAdd(Type typeObj){
		if (insertMethod == null) {
			try {
				insertMethod = mapper.getClass().getMethod("insert",new Class[]{typeClass});
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

	@Transactional
	public Integer baseEdit(Type typeObj){
		if (updateByPrimaryKeyMethod == null) {
			try {
				updateByPrimaryKeyMethod = mapper.getClass().getMethod("updateByPrimaryKey",new Class[]{typeClass});
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

	@Transactional
	public Integer baseDeleteByExample(Example example){
		if (deleteByExampleMethod == null) {
			try {
				updateByPrimaryKeyMethod = mapper.getClass().getMethod("updateByPrimaryKey",new Class[]{exampleClass});
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
				selectByPrimaryKeyMethod = mapper.getClass().getMethod("selectByPrimaryKey",new Class[]{pkClass});
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
				selectByExampleMethod = mapper.getClass().getMethod("selectByExample",new Class[]{exampleClass});
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
				countByExampleMethod = mapper.getClass().getMethod("countByExample",new Class[]{exampleClass});
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
				selectByExampleWithRowboundsMethod = mapper.getClass().getMethod("selectByExampleWithRowbounds",new Class[]{exampleClass,RowBounds.class});
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
	@Transactional
	public Integer baseDeleteByPrimaryKey(PK pk){
		if (deleteByPrimaryKeyMethod == null) {
			try {
				deleteByPrimaryKeyMethod = mapper.getClass().getMethod("deleteByPrimaryKey",new Class[]{pkClass});
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

	public Example createExample() {
		try {
			return this.exampleClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected Mapper getMapper() {
		return this.mapper;
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
