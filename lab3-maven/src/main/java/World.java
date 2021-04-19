import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class World {
    protected int id;
    public String worldName;
    protected List<Entity> entities;

    public World(int id, String worldName) {
        this.id = id;
        this.worldName = worldName;
        this.entities = new LinkedList<>();
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void update() {
        for (Entity entity : entities) {
            entity.update();
        }
        for (int i = entities.size() - 1; i >= 0; i--) {
            if (entities.get(i).getHealth() < 0) {
                entities.remove(i);
            }
        }
    }

    public List<Entity> getEntitiesInRegion(int x, int z, double range) {
        class EntityWithDistance {
            final Entity entity;
            final double distance;

            EntityWithDistance(Entity entity, double distance) {
                this.entity = entity;
                this.distance = distance;
            }
        }

        List<EntityWithDistance> entitiesWithDistance = new LinkedList<>();
        for (Entity entity : this.entities) {
            double distance = entity.distanceTo(x, z);
            if (distance <= range)
                entitiesWithDistance.add(new EntityWithDistance(entity, distance));
        }

        entitiesWithDistance.sort(Comparator.comparingDouble(e -> e.distance));

        List<Entity> closestEntities = new LinkedList<>();

        for (EntityWithDistance entity : entitiesWithDistance) {
            closestEntities.add(entity.entity);
        }

        return closestEntities;
    }

    public List<Entity> getEntitiesNearEntity(Entity entity, double range) {
        return this.getEntitiesInRegion((int)entity.posX, (int)entity.posZ, range);
    }

    @Override
    public String toString() {
        return "World{" +
                "id=" + id +
                ", worldName='" + worldName + '\'' +
                ", entities=" + entities +
                '}';
    }
}
