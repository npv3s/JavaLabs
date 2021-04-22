public class Zoo {
    String title;
    EntityLiving[] animals;

    public Zoo(String title, EntityLiving[] animals) {
        this.title = title;
        this.animals = animals;
    }

    public int getFlyEntityCount() {
        int c = 0;
        for (EntityLiving animal : animals) {
            if (animal instanceof IFlyEntity)
                c++;
        }
        return c;
    }

    public int getPredatorEntityCount() {
        int c = 0;
        for (EntityLiving animal : animals) {
            if (animal instanceof IPredator)
                c++;
        }
        return c;
    }

    public int getSoundEntityCount() {
        int c = 0;
        for (EntityLiving animal : animals) {
            if (animal instanceof ISoundEntity)
                c++;
        }
        return c;
    }

    public void feedPredators(EntityLiving entity) {
        if (entity == null) return;
        if (entity instanceof IPredator) {
            System.out.println("Этим кормить нельзя (" + entity + ")");
        } else {
            for (EntityLiving animal : animals) {
                if (animal instanceof IPredator)
                    ((IPredator) animal).hunt(entity);
            }
        }
    }
}
