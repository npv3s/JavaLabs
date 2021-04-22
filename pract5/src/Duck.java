public class Duck extends EntityLiving implements IFlyEntity, ISoundEntity {
    Duck() {
        this.title = "Утка";
        this.type = "птица";
    }

    @Override
    public double getMaxFlyHeight() {
        return 1000;
    }

    @Override
    public double getMaxFlyTime() {
        return 10;
    }

    @Override
    public String getSound() {
        return "кря-кря";
    }

    @Override
    public String toString() {
        return this.title;
    }
}
