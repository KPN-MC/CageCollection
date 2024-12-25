package dev.ev1dent.cagecollection.utilities;

import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.EntityType;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {

    public Component errorMM(String s){
        return MiniMessage.miniMessage().deserialize("<dark_red>" + s).decorationIfAbsent(TextDecoration.ITALIC, TextDecoration.State.FALSE);
    }
    public Component formatMM(String s){
        return MiniMessage.miniMessage().deserialize("<green>" + s).decorationIfAbsent(TextDecoration.ITALIC, TextDecoration.State.FALSE);
    }

    public List<EntityType> getLivingEntities() {
        return Stream.of(EntityType.values())
                .collect(Collectors.toList());
    }

    public List<String> getEntityNames(List<EntityType> entities) {
        return entities.stream()
                .map(entity -> entity.name().toLowerCase())
                .collect(Collectors.toList());
    }
}
