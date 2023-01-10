package hello.core.sigleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    //private 생성자로 외부에서의 객체생성을 막는다.
    private SingletonService() {}

    public  void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }


}
