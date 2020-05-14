package com.rvandoosselaer.blocks;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * A thread safe register for shapes. The register is used so only one instance of a shape is used throughout the Blocks
 * framework.
 *
 * @author rvandoosselaer
 */
@Slf4j
public class ShapeRegistry {

    private final ConcurrentMap<String, Shape> shapeRegistry = new ConcurrentHashMap<>();

    public ShapeRegistry() {
        registerDefaultShapes();
    }

    public Shape register(@NonNull String name, Shape shape) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Invalid shape name " + name + " specified.");
        }

        shapeRegistry.put(name, shape);
        if (log.isTraceEnabled()) {
            log.trace("Registered shape {} -> {}", name, shape);
        }
        return shape;
    }

    public Shape get(String name) {
        Shape s = shapeRegistry.get(name);
        if (s == null) {
            log.warn("No shape registered with name {}", name);
        }
        return s;
    }

    public boolean remove(@NonNull String name) {
        if (shapeRegistry.containsKey(name)) {
            Shape shape = shapeRegistry.remove(name);
            if (log.isTraceEnabled()) {
                log.trace("Removed shape {} -> {}", name, shape);
            }
            return true;
        }
        return false;
    }

    public void clear() {
        shapeRegistry.clear();
    }

    public Collection<String> getAll() {
        return Collections.unmodifiableCollection(shapeRegistry.keySet());
    }

    public void registerDefaultShapes() {
        register(ShapeIds.CUBE, new Cube());
        register(ShapeIds.SLAB, new Slab(0, 1f / 3f));
        register(ShapeIds.SLAB_TOP, new Slab(2f / 3f, 1));
        register(ShapeIds.DOUBLE_SLAB, new Slab(0, 2f / 3f));
        register(ShapeIds.DOUBLE_SLAB_TOP, new Slab(1f / 3f, 1));
        register(ShapeIds.PLATE, new Slab(0, 0.1f));
        register(ShapeIds.STAIRS_BACK, new Stair(Direction.NORTH));
        register(ShapeIds.STAIRS_FRONT, new Stair(Direction.SOUTH));
        register(ShapeIds.STAIRS_LEFT, new Stair(Direction.WEST));
        register(ShapeIds.STAIRS_RIGHT, new Stair(Direction.EAST));
        register(ShapeIds.WEDGE_BACK, new Wedge(Direction.NORTH));
        register(ShapeIds.WEDGE_FRONT, new Wedge(Direction.SOUTH));
        register(ShapeIds.WEDGE_LEFT, new Wedge(Direction.WEST));
        register(ShapeIds.WEDGE_RIGHT, new Wedge(Direction.EAST));
        register(ShapeIds.PYRAMID, new Pyramid());
        register(ShapeIds.ROUNDED_CUBE, new RoundedCube());
        register(ShapeIds.PYRAMID_INVERTED, new InvertedPyramid());
        register(ShapeIds.STAIRS_INVERTED_BACK, new InvertedStair(Direction.NORTH));
        register(ShapeIds.STAIRS_INVERTED_FRONT, new InvertedStair(Direction.SOUTH));
        register(ShapeIds.STAIRS_INVERTED_LEFT, new InvertedStair(Direction.WEST));
        register(ShapeIds.STAIRS_INVERTED_RIGHT, new InvertedStair(Direction.EAST));
        register(ShapeIds.WEDGE_INVERTED_BACK, new InvertedWedge(Direction.NORTH));
        register(ShapeIds.WEDGE_INVERTED_FRONT, new InvertedWedge(Direction.SOUTH));
        register(ShapeIds.WEDGE_INVERTED_LEFT, new InvertedWedge(Direction.WEST));
        register(ShapeIds.WEDGE_INVERTED_RIGHT, new InvertedWedge(Direction.EAST));
        register(ShapeIds.STAIRS_INNER_CORNER_FRONT, new StairInnerCorner(Direction.SOUTH));
        register(ShapeIds.STAIRS_INNER_CORNER_LEFT, new StairInnerCorner(Direction.WEST));
        register(ShapeIds.STAIRS_INNER_CORNER_RIGHT, new StairInnerCorner(Direction.EAST));
        register(ShapeIds.STAIRS_INNER_CORNER_BACK, new StairInnerCorner(Direction.NORTH));
        register(ShapeIds.STAIRS_OUTER_CORNER_FRONT, new StairOuterCorner(Direction.SOUTH));
        register(ShapeIds.STAIRS_OUTER_CORNER_LEFT, new StairOuterCorner(Direction.WEST));
        register(ShapeIds.STAIRS_OUTER_CORNER_RIGHT, new StairOuterCorner(Direction.EAST));
        register(ShapeIds.STAIRS_OUTER_CORNER_BACK, new StairOuterCorner(Direction.NORTH));
        register(ShapeIds.SQUARE_BACK, new Square(Direction.NORTH));
        register(ShapeIds.SQUARE_FRONT, new Square(Direction.SOUTH));
        register(ShapeIds.SQUARE_LEFT, new Square(Direction.WEST));
        register(ShapeIds.SQUARE_RIGHT, new Square(Direction.EAST));
        register(ShapeIds.SQUARE_TOP, new Square(Direction.UP));
        register(ShapeIds.SQUARE_BOTTOM, new Square(Direction.DOWN));
        register(ShapeIds.POLE, new Pole());
        register(ShapeIds.CUBE_UPSIDE_DOWN, new RotatedCube(Direction.DOWN));
        register(ShapeIds.CUBE_LEFT, new RotatedCube(Direction.WEST));
        register(ShapeIds.CUBE_RIGHT, new RotatedCube(Direction.EAST));
        register(ShapeIds.CUBE_FRONT, new RotatedCube(Direction.SOUTH));
        register(ShapeIds.CUBE_BACK, new RotatedCube(Direction.NORTH));
        register(ShapeIds.STAIRS_OUTER_CORNER_INVERTED_FRONT, new StairOuterCornerInverted(Direction.SOUTH));
        register(ShapeIds.STAIRS_OUTER_CORNER_INVERTED_LEFT, new StairOuterCornerInverted(Direction.WEST));
        register(ShapeIds.STAIRS_OUTER_CORNER_INVERTED_RIGHT, new StairOuterCornerInverted(Direction.EAST));
        register(ShapeIds.STAIRS_OUTER_CORNER_INVERTED_BACK, new StairOuterCornerInverted(Direction.NORTH));
    }

}
