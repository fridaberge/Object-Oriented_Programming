public class testVaarArray {
    public static void main(String[] args) {
        VaarArray<String> mittArray = new VaarArray<String>(10);
        mittArray.settInn("hei");
        mittArray.settInn("hei");
        mittArray.settInn("hei");

        for(String element:  mittArray){
            System.out.println(element);
        }
    }
}
