package com.sz21c.springbootwebappsample.sampleservice.mapper;

import org.springframework.stereotype.Repository;

/**
 * Mapper.
 * 이 interface와 같은 package에 sqlmap mapper(동일 이름)가 있는 경우 별도의 구현체가 없더라도 sqlmap의 쿼리를 실행시킬 수 있다.
 * /src/main/java/com/sz21c/springbootwebappsample/sampleservice/mapper/SampleMapper.xml 참고
 */
@Repository
public interface SampleMapper {
    String selectMapperSql();
}
