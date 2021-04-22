public class EntityPlayer extends Entity {
    private String nickname;

    public EntityPlayer(String nickname, double posX, double posZ, int maxHealth, int health, int attackDamage) {
        super("Player", posX, posZ, false, maxHealth, health, attackDamage);
        this.nickname = nickname;
    }

    public void update() {
        super.update();
        if ((health < maxHealth) & (GameServer.getInstance().getUpdate() % 2 == 0)) {
            health++;
        }
    }

    @Override
    public String toString() {
        return "EntityPlayer{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", posX=" + posX +
                ", posZ=" + posZ +
                ", agressive=" + aggressive +
                ", maxHealth=" + maxHealth +
                ", health=" + health +
                ", attackDamage=" + attackDamage +
                ", nickname='" + nickname + '\'' +
                '}';
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
