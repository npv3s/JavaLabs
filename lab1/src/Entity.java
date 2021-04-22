public class Entity {
    private static int idCounter = 1;
    protected final long id;
    protected String title;
    protected double posX;
    protected double posZ;
    protected boolean aggressive;
    protected int maxHealth;
    protected int health;
    protected int attackDamage;

    public Entity(String title, double posX, double posZ, boolean aggressive, int maxHealth, int health, int attackDamage) {
        this.id = idCounter;
        idCounter++;
        this.title = title;
        this.posX = posX;
        this.posZ = posZ;
        this.aggressive = aggressive;
        this.maxHealth = maxHealth;
        this.health = health;
        this.attackDamage = attackDamage;
    }

    public void update() {
        if (aggressive) {
            double min = Double.MAX_VALUE;
            double x = 0, z = 0;
            Entity closest = null;
            for (Entity e : GameServer.getInstance().entities) {
                if (e != null) {
                    if (!e.equals(this) && !(e.isAggressive())) {
                        x = posX - e.posX;
                        z = posZ - e.posZ;
                        double distance = Math.sqrt(Math.pow(Math.abs(x), 2) + Math.pow(Math.abs(z), 2));
                        if (distance < min) {
                            min = distance;
                            closest = e;
                        }
                    }
                }
            }
            if (closest != null) {
                if (min < 2)
                    this.attack(closest);
                else if (min < 20) {
                    if (z > 1.0)
                        posZ--;
                    else if (z < -1.0)
                        posZ++;
                    if (x > 1.0)
                        posX--;
                    else if (x < -1.0)
                        posX++;
                }
            }
        }
    }

    public void attack(Entity entity) {
        int health = entity.getHealth();
        health -= this.attackDamage + 0.5 * GameServer.getInstance().difficulty;
        if (health > 0) {
            entity.setHealth(health);
            if (entity instanceof EntityPlayer)
                entity.attack(this);
        } else {

            String killer = this.getTitle();
            if (this instanceof EntityPlayer)
                killer = ((EntityPlayer) this).getNickname();

            String killed = entity.getTitle();
            if (entity instanceof EntityPlayer)
                killed = ((EntityPlayer) entity).getNickname();

            System.out.println(killer + " убил " + killed);
            Entity[] entities = GameServer.getInstance().entities;
            for (int i = 0; i < entities.length; i++) {
                if (entities[i] != null) {
                    if (entities[i].equals(entity)) {
                        entities[i] = null;
                        break;
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", posX=" + posX +
                ", posZ=" + posZ +
                ", aggressive=" + aggressive +
                ", maxHealth=" + maxHealth +
                ", health=" + health +
                ", attackDamage=" + attackDamage +
                '}';
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosZ() {
        return posZ;
    }

    public void setPosZ(double posZ) {
        this.posZ = posZ;
    }

    public boolean isAggressive() {
        return aggressive;
    }

    public void setAggressive(boolean aggressive) {
        this.aggressive = aggressive;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }
}
