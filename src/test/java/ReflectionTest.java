//import org.apache.catalina.User;
import org.example.annotation.Controller;
import org.example.annotation.Service;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.example.model.User;


import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ReflectionTest {

    private  static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

@Test
    void controllerScan(){

    Set<Class<?>> beans = getTypesAnnotationWith(List.of(Controller.class, Service.class));
    logger.debug("beans : [{}]", beans);
}

    @Test
    void showClass() {

    Class<User> clazz = User.class;
    logger.debug(clazz.getName());

    logger.debug("User all decleared fiedls : [{}]", Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toList()));
    logger.debug("User all decleared constructors : [{}]", Arrays.stream(clazz.getDeclaredConstructors()).collect(Collectors.toList()));
    logger.debug("User all declaered methods : [{}]", Arrays.stream(clazz.getDeclaredMethods()).collect(Collectors.toList()));

    }

    @Test
    void load() throws ClassNotFoundException {
        // 힙영역에 로드되어 있는 클래스 타입의 객체 가져오는 3가지 방법

        // 1
        Class<User> clazz = User.class;
        //2
        User user = new User("park","junvelop");
        Class<? extends User> clazz2 = user.getClass();
        //3
        Class<?> clazz3 = Class.forName("org.example.model.User");

        logger.debug("clazz : [{}]", clazz);
        logger.debug("clazz2 : [{}]", clazz2);
        logger.debug("clazz3 : [{}]", clazz3);

        assertThat(clazz == clazz2).isTrue();
        assertThat(clazz2 == clazz3).isTrue();
        assertThat(clazz3 == clazz).isTrue();



    }

    private static Set<Class<?>> getTypesAnnotationWith(List<Class<? extends Annotation>> annotations) {
        Reflections reflections = new Reflections("org.example");

        Set<Class<?>> beans = new HashSet<>();
        annotations.forEach(annotation -> beans.addAll(reflections.getTypesAnnotatedWith(annotation)));

        return beans;
    }

    ;

}
