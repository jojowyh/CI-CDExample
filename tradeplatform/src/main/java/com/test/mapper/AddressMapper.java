package com.test.mapper;

import com.test.pojo.entity.Address;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 86187
* @description 针对表【address(地址表)】的数据库操作Mapper
* @createDate 2024-06-27 10:32:19
* @Entity com.test.pojo.entity.Address
*/
@Mapper
public interface AddressMapper {
    /**
     * 根据条件查询地址列表
     * @param address
     * @return
     */
    List<Address> queryByCondition(Address address);

    /**
     * 添加用户的地址
     * @param address
     */
    @Insert("insert into trade_platform.address (user_id,receiver, phone,add_detail,is_defaul)" +
            "values (#{userId},#{receiver},#{phone},#{addDetail},#{isDefaul})")
    void save(Address address);
}




