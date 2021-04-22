public interface IPredator {
    public default void hunt(EntityLiving entity) {
        System.out.println(this.toString() + " съел " + entity.toString());
    }
}
