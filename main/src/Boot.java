public class Boot {
    public static void main(String[] args) {
        StationModel stationModel = new StationModel();
        Window view1 = new Window();
        StationViewModel model1 = new StationViewModel(view1, stationModel);
        Window view2 = new Window();
        StationViewModel model2 = new StationViewModel(view2, stationModel);
    }
}
