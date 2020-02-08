package net.work100.training.stage2.design.patterns.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: CriteriaFemale</p>
 * <p>Description: 过滤女性</p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-design-patterns-filter-pattern.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-08 14:44
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-08   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class CriteriaFemale implements Criteria {
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> femalePersons = new ArrayList<Person>();
        for (Person person : persons) {
            if (person.getGender().equalsIgnoreCase("FEMALE")) {
                femalePersons.add(person);
            }
        }
        return femalePersons;
    }
}
