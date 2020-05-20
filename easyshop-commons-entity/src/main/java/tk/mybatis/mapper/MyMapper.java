package tk.mybatis.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author LiuHaonan
 * @date 17:54 2020/5/15
 * @email acerola.orion@foxmail.com
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
