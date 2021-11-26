public class Boot {
    public static void main(String[] args) {
        Window view1 = new Window();
        view1.initialiseWindow();
        StationViewModel m = new StationViewModel(view1);
    }
}
