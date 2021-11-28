import java.net.SocketPermission;

public class ThreadAssignment {

    static class Counter {
        void count() {
            for (int i=350;i>0;i--){
                System.out.println(i);
            }
            System.out.println("Finish !");
        }
    }

    static class MyThread extends Thread {
        private final Counter counter;

        public MyThread(Counter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            synchronized(counter) {
                counter.count();
            }
        }
    }

    public static void main(String[] args) {
        Counter counter = new Counter();

        MyThread firstone =new MyThread(counter);
        firstone.start();
        
        MyThread secondone =new MyThread(counter);
        secondone.start();

        try {
            firstone.join();
            secondone.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("DONE!");
    }
    
}
