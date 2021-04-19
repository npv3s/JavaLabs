import java.util.List;

public class Entity {
    private static int idCounter = 1;
    protected final long id;
    protected World world;
    public String title;
    protected double posX;
    protected double posZ;
    protected boolean aggressive;
    protected int maxHealth;
    protected int health;
    protected int attackDamage;
    protected Entity target;

    public Entity(World world, String title, double posX, double posZ, boolean aggressive, int maxHealth, int health, int attackDamage) {
        this.id = idCounter++;
        this.world = world;
        this.title = title;
        this.posX = posX;
        this.posZ = posZ;
        this.aggressive = aggressive;
        this.maxHealth = maxHealth;
        this.health = health;
        this.attackDamage = attackDamage;
        this.target = null;
    }

    public void update() {
        if (aggressive) {
            searchTarget();
            if (target != null) {
                double x = this.posX - target.posX;
                double z = this.posZ - target.posZ;
                double distance = target.distanceTo(this.posX, this.posZ);
                if (distance < 2) {
                    this.attack(target);
                    if (target.getHealth() <= 0)
                        target = null;
                } else {
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

    public void searchTarget() {
        if (target == null) {
            List<Entity> closestEntities = world.getEntitiesNearEntity(this, 20);
            if (!closestEntities.isEmpty()) {
                Entity target = closestEntities.get(0);
                if (this.equals(target))
                    if (closestEntities.size() >= 2)
                        target = closestEntities.get(1);
                    else
                        return;
                double distance = target.distanceTo(posX, posZ);
                if (distance < 20)
                    this.target = target;
            }
        }
    }

    public void attack(Entity entity) {
        int health = entity.getHealth();
        if (health <= 0) return;
        health -= this.attackDamage + 0.5 * GameServer.getInstance().getConfig().difficulty;
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
            // world.entities.remove(entity);
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

    public double distanceTo(double x, double z) {
        x -= this.posX;
        z -= this.posZ;
        return Math.sqrt(Math.pow(Math.abs(x), 2) + Math.pow(Math.abs(z), 2));
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
