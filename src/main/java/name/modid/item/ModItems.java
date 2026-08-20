package name.modid.item;

import name.modid.MinecraftMeals;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;

public final class ModItems {
	public static final Item CARROT_STEW = registerFood("carrot_stew", 7, 0.6F, new Item.Properties().stacksTo(16).craftRemainder(Items.BOWL));
	public static final Item BEETROOT_BREAD = registerFood("beetroot_bread", 6, 0.6F);
	public static final Item HONEYED_APPLE = registerFood("honeyed_apple", 6, 0.5F);
	public static final Item SWEET_BERRY_PIE = registerFood("sweet_berry_pie", 8, 0.3F);
	public static final Item GLOW_BERRY_COOKIE = registerFood("glow_berry_cookie", 3, 0.2F);
	public static final Item BAKED_CARROT = registerFood("baked_carrot", 5, 0.6F);
	public static final Item MUSHROOM_PIE = registerFood("mushroom_pie", 7, 0.6F);
	public static final Item MELON_JAM_TOAST = registerFood("melon_jam_toast", 6, 0.5F);
	public static final Item POTATO_CAKES = registerFood("potato_cakes", 5, 0.6F);
	public static final Item PUMPKIN_SOUP = registerFood("pumpkin_soup", 7, 0.6F, new Item.Properties().stacksTo(16).craftRemainder(Items.BOWL));

	public static final List<Item> FOODS = List.of(
			CARROT_STEW,
			BEETROOT_BREAD,
			HONEYED_APPLE,
			SWEET_BERRY_PIE,
			GLOW_BERRY_COOKIE,
			BAKED_CARROT,
			MUSHROOM_PIE,
			MELON_JAM_TOAST,
			POTATO_CAKES,
			PUMPKIN_SOUP
	);

	private static final ResourceKey<CreativeModeTab> MEALS_TAB_KEY = ResourceKey.create(Registries.CREATIVE_MODE_TAB, MinecraftMeals.id("meals"));
	public static final CreativeModeTab MEALS_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, MEALS_TAB_KEY,
			FabricCreativeModeTab.builder()
					.title(Component.translatable("itemGroup.minecraft-meals.meals"))
					.icon(() -> new ItemStack(CARROT_STEW))
					.displayItems((parameters, output) -> FOODS.forEach(output::accept))
					.build());

	private ModItems() {
	}

	public static void initialize() {
		MinecraftMeals.LOGGER.info("Registered {} vanilla-style food items for {}", FOODS.size(), MinecraftMeals.MOD_ID);
	}

	private static Item registerFood(String name, int nutrition, float saturation) {
		return registerFood(name, nutrition, saturation, new Item.Properties());
	}

	private static Item registerFood(String name, int nutrition, float saturation, Item.Properties properties) {
		ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, MinecraftMeals.id(name));
		return Registry.register(BuiltInRegistries.ITEM, itemKey,
				new Item(properties.setId(itemKey).food(new FoodProperties(nutrition, saturation, false))));
	}
}
