# Exoverse UI/UX Standards and Reference Library

## Purpose

This document records the UI/UX principles and external component resources to consult when designing and implementing the Exoverse frontend. These resources are references, not mandatory dependencies. Do not install a library merely because it appears in this list.

## Project skills

Use the skill that matches the work being performed:

- `.claude/skills/ux-motion/SKILL.md` — animation, transitions, hover/press feedback, menus, modals, toasts, drag interactions and other moving interfaces.
- `.claude/skills/ui-principles/SKILL.md` — layout stability, loading states, forms, validation, drag and drop, menus, modals, progress, carousels, scrolling and feedback.

For a screen involving both layout/interaction and motion, consult both skills. Apply only the principles relevant to the particular screen; do not animate for decoration alone.

## External reference resources

### React Bits

- Website: https://reactbits.dev/
- Use for: animated React components, visual backgrounds, card treatments, galleries, text effects and selected interactive details.
- Exoverse fit: consider for occasional atmospheric or expressive elements on the exploration experience. Keep effects subordinate to scientific content and navigation.
- Adoption note: inspect each component's implementation, dependencies, accessibility and performance before copying it.

### Watermelon UI

- Website: https://ui.watermelon.sh/
- Use for: React UI components, page sections, blocks, templates and layout inspiration.
- Exoverse fit: useful as a source of layout patterns and reusable starting points for landing pages and dashboard-like areas.
- Adoption note: adapt components to Exoverse design tokens and accessibility requirements rather than importing a complete visual system unchanged.

### Kokonut UI

- Website: https://kokonutui.com/
- Use for: open-source React components for AI chat and agent interfaces, plus supporting inputs, cards, buttons, navigation and feedback.
- Exoverse fit: particularly relevant to the future Astronomy Research Assistant experience.
- Adoption note: the current library is built around React, Tailwind CSS, Motion and shadcn/ui. Check compatibility with the frontend stack before adopting components. Defer AI-specific UI until the relevant Exoverse roadmap milestone.

### Apache ECharts

- Website: https://echarts.apache.org/en/
- Use for: interactive scientific charts, time series, scatter plots, heatmaps, graph/network views, maps and other data visualisations.
- Exoverse fit: a strong candidate for scientific measurements and data exploration where users need to inspect values, ranges, comparisons or trends.
- Adoption note: choose the chart type to fit the data and the scientific question. Preserve axis labels, units, uncertainty, provenance and accessible descriptions. ECharts GL is a separate extension for WebGL-based 3D charting; do not treat it as a replacement for a dedicated planetary 3D renderer.

### Anime.js

- Website: https://animejs.com/
- Use for: specialised JavaScript animation sequences, SVG animation and effects that are not well served by the primary UI motion approach.
- Exoverse fit: optional tool for a distinct, technically justified effect.
- Adoption note: avoid introducing a second animation engine for ordinary interface transitions. Check bundle cost, lifecycle cleanup, reduced-motion behaviour and interaction interruption before use.

### Motion Primitives

- Website: https://motion-primitives.com/
- Use for: reusable animated React components and interaction patterns built with Motion.
- Exoverse fit: a possible source for polished transitions, text treatments, menus, cards and other interface components.
- Adoption note: components are built with Motion and Tailwind CSS. Review styling and dependency requirements before integrating them; copy only the components that fit the existing design system.

### Bklit UI

- Website: https://ui.bklit.com/
- Documentation: https://bklit.com/docs/components
- Use for: prebuilt React chart components such as line, area, scatter, radar, heatmap, gauge, sankey and related data displays.
- Exoverse fit: consider when a ready-made chart component is more useful than implementing the visualisation directly with ECharts.
- Adoption note: review the licence of the exact asset being reused. The chart component source is described as MIT-licensed, while Bklit Studio is proprietary; do not assume the Studio itself can be copied or redistributed.

## Frontend adoption policy

1. Keep the Exoverse component system and design tokens as the source of truth. External libraries provide selected components or implementation references; they do not define the entire product design.
2. Prefer one primary motion approach for everyday UI. Avoid overlapping animation libraries unless a specific requirement justifies the additional dependency.
3. Do not add any of these libraries during backend-only milestones just to prepare for possible future use.
4. Before adopting a component, check its licence, dependencies, bundle impact, responsive behaviour, keyboard support, screen-reader behaviour, reduced-motion support and maintenance status.
5. Scientific visualisations must prioritise correctness and legibility over spectacle. Never distort values, hide uncertainty, imply unsupported precision or make essential labels available only through hover.
6. Keep animation purposeful, interruptible where appropriate, and respectful of `prefers-reduced-motion`.
7. Prefer native browser scrolling and controls unless a custom interaction provides a clear user benefit.
8. Re-evaluate current library documentation and package compatibility at the time of implementation; this reference list is not a promise that a package version or API will remain unchanged.

## Roadmap alignment

- **M3–M5:** Backend and data-platform work. These UX references are documentation only; do not add frontend dependencies as part of these backend milestones.
- **M6:** Apply the relevant layout, interaction, visualisation and motion standards when building the experience layer.
- **M7:** Review polish, accessibility, reduced motion, responsiveness, performance and interaction consistency.
- **Future AI Assistant milestones:** Evaluate Kokonut UI and other assistant-oriented components when the assistant interface is in scope.

## Source links

- React Bits: https://reactbits.dev/
- Watermelon UI: https://ui.watermelon.sh/
- Kokonut UI: https://kokonutui.com/
- Apache ECharts: https://echarts.apache.org/en/
- Anime.js: https://animejs.com/
- Motion Primitives: https://motion-primitives.com/
- Bklit UI: https://ui.bklit.com/
