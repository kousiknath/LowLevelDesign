package lld.kafka.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Topic {
    String id;
    String name;
    List<Partition> partitions;
    final int maxPartitions;

    public Topic(String name, int numPartitions) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.maxPartitions = numPartitions;
        this.partitions = new ArrayList<>(maxPartitions);
        generatePartitions();
    }

    private void generatePartitions() {
        for (int index = 0; index < this.maxPartitions; index++) {
            this.partitions.add(new Partition(this));
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMaxPartitions() {
        return maxPartitions;
    }

    public List<Partition> getPartitions() {
        return this.partitions;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        if (o == this) {
            return true;
        }

        Topic other = (Topic) o;
        return this.id.equals(other.id);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
