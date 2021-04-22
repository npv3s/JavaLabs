public class Main {
    public static void main(String[] args) {
        Osprey osprey = new Osprey();
        Fish fish = new Fish();
        Duck duck = new Duck();
        Zoo zoo = new Zoo("Зоопарк", new EntityLiving[]{osprey, fish, duck});

        System.out.println(duck.getSound());
        System.out.println(fish.getSound());

        System.out.println("Летающих " + zoo.getFlyEntityCount());
        System.out.println("Хищников " + zoo.getPredatorEntityCount());
        System.out.println("Издают звуки " + zoo.getSoundEntityCount());

        zoo.feedPredators(fish);
        zoo.feedPredators(osprey);
    }
}
