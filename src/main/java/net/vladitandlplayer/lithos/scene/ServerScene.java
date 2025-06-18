package net.vladitandlplayer.lithos.scene;

import java.util.*;

public class ServerScene {

    private final Map<Integer, SceneObject> objects = new HashMap<>();
    private final Set<Integer> created = new HashSet<>();
    private final Set<Integer> updated = new HashSet<>();
    private final Set<Integer> removed = new HashSet<>();

    // Add or update an object by ID
    public void putObject(int id, SceneObject object) {
        boolean isNew = !objects.containsKey(id);
        objects.put(id, object);

        if (isNew) {
            created.add(id);
        } else {
            updated.add(id);
        }
        removed.remove(id); // in case it was marked removed before
    }

    // Remove an object by ID
    public void removeObject(int id) {
        if (objects.containsKey(id)) {
            objects.remove(id);
            removed.add(id);
            created.remove(id);
            updated.remove(id);
        }
    }

    public Collection<SceneObject> getCreatedObjects() {
        List<SceneObject> list = new ArrayList<>();
        for (int id : created) {
            SceneObject obj = objects.get(id);
            if (obj != null) list.add(obj);
        }
        return list;
    }

    public Collection<SceneObject> getUpdatedObjects() {
        List<SceneObject> list = new ArrayList<>();
        for (int id : updated) {
            SceneObject obj = objects.get(id);
            if (obj != null) list.add(obj);
        }
        return list;
    }

    public Set<Integer> getRemovedIds() {
        return Collections.unmodifiableSet(removed);
    }

    // Clear update sets after syncing
    public void clearUpdateSets() {
        created.clear();
        updated.clear();
        removed.clear();
    }
}
