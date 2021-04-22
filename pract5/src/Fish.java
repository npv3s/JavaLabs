public class Fish extends EntityLiving implements ISoundEntity {
    Fish() {
        this.title = "Рыба";
        this.type = "рыба";
    }

    @Override
    public String getSound() {
        return "буль-буль";
    }

    @Override
    public String toString() {
        return this.title;
    }
}
