package dev.ev1dent.cagecollection.Commands.tabCompletion;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CageTC implements TabCompleter {
    private final String[] inputArgs = { "give", "set"};
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
        final List<String> completions = new ArrayList<>();

            switch (args.length) {
                case 1: {
                    StringUtil.copyPartialMatches(args[0], Arrays.asList(inputArgs), completions);
                    Collections.sort(completions);
                    return completions;
                }
                case 2: {
                    return null;
                }
                case 3:
                    return getEntityNames(getLivingEntities());
            }
        return null;
    }

    private List<EntityType> getLivingEntities() {
        return Stream.of(EntityType.values())
                .collect(Collectors.toList());
    }

    private List<String> getEntityNames(List<EntityType> entities) {
        return entities.stream()
                .map(entity -> entity.name().toLowerCase())
                .collect(Collectors.toList());
    }



}
