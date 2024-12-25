package dev.ev1dent.cagecollection.Commands.tabCompletion;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CageTC implements TabCompleter {
    private final String[] inputArgs = { "give", "set"};
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
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
                    List<String> livingEntityNames = getAllLivingEntityNames();
                    StringUtil.copyPartialMatches(args[2], Arrays.asList(inputArgs), livingEntityNames);
                    Collections.sort(completions);
                    return completions;
            }
        return null;
    }

    public static List<String> getAllLivingEntityNames() {
        List<String> livingEntityNames = new ArrayList<>();
        for (EntityType entityType : EntityType.values()) {
            // Check if the EntityType is a living entity
            if (entityType.getEntityClass() != null && LivingEntity.class.isAssignableFrom(entityType.getEntityClass())) {
                // Add the name of the living entity to the list
                livingEntityNames.add(entityType.name());
            }
        }

        return livingEntityNames;
    }
}
