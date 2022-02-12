package xyz.qiuqian.stream;

import java.util.Arrays;
import java.util.List;

/**
 * 1. id 为偶数
 * 2. 年龄大于23岁
 * 3. 用户名转化为大写
 * 4. 倒序用户名
 * 5. 只输出一个
 */
public class Test {
    public static void main(String[] args) {
        User u1 = new User(1, "a", 21);
        User u2 = new User(2, "b", 22);
        User u3 = new User(3, "c", 23);
        User u4 = new User(4, "d", 24);
        User u5 = new User(5, "e", 25);

        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);

        list.stream()
                .filter(user -> { return user.getId() % 2 == 0; })
                .filter(user -> { return user.getAge() > 23; })
                .map(user -> { return user.getName().toUpperCase(); })
                .sorted((user1, user2) -> { return user2.compareTo(user1); })
                .limit(1)
                .forEach(System.out::println);
    }
}
