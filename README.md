# effectboots
Boots with particle effects

Commands:\
`/effectboots` - Opens the gui (requires `effectboots.use` permission)

## Api
You can add your own effect boots using the api. To add an effect boot simply create a new class which extends `EffectBoot` and implement the required methods, like this:
```java
public class MyBoots extends EffectBoot {

    public MyBoots() {
        super("my-boots");
    }

    @Override
    public void playEffect(Player player, Location location) {
        location.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, location, 1, 0, 0, 0);
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemBuilder(Material.LEATHER_BOOTS)
                .setDisplayName("§bSome cool boots")
                .setLeatherColor(Color.WHITE)
                .build();
    }

}
```

To register your boots you need to add these lines to your `onEnable` method:
```java
EffectBootsPlugin effectBootsPlugin = JavaPlugin.getPlugin(EffectBootsPlugin.class);
effectBootsPlugin.registerEffectBoots(new MyBoots());
```

> **IMPORTANT**
> You need to add the effect boots plugin (EffectBoots) as a depend or a softdepend in your plugin.yml, otherwise you cannot register your own boots!
