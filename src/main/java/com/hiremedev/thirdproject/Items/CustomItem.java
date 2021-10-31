package com.hiremedev.thirdproject.Items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.format.TextStyle;

public abstract class CustomItem extends ItemStack {
    public CustomItem(Material material, int amount, String name, NamedTextColor color, NamespacedKey namespacedKey, String value){
        super(material, amount);
        var meta = this.getItemMeta();
        var data = meta.getPersistentDataContainer();
        meta.displayName(Component
                .text(name)
                .color(color)
        );
        data.set(namespacedKey, PersistentDataType.STRING,value);
        this.setItemMeta(meta);
    }
}
