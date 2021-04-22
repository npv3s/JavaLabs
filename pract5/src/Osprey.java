public class Osprey extends EntityLiving implements IFlyEntity, IPredator {
    Osprey() {
        this.title = "Скопа";
        this.type = "птица";
    }

    @Override
    public double getMaxFlyHeight() {
        return 2000;
    }

    @Override
    public double getMaxFlyTime() {
        return 30;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
