public class Outer {
    public Outer() {
        System.out.println("outer!!");
    }

    public static class Inner {
        public Inner() {
            System.out.println("inner!!");
        }
    }
}
