import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore
const Entities = () => import('@/entities/entities.vue');

// prettier-ignore
const Menu = () => import('@/entities/menu/menu.vue');
// prettier-ignore
const MenuUpdate = () => import('@/entities/menu/menu-update.vue');
// prettier-ignore
const MenuDetails = () => import('@/entities/menu/menu-details.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default {
  path: '/',
  component: Entities,
  children: [
    {
      path: 'menu',
      name: 'Menu',
      component: Menu,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'menu/new',
      name: 'MenuCreate',
      component: MenuUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'menu/:menuId/edit',
      name: 'MenuEdit',
      component: MenuUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'menu/:menuId/view',
      name: 'MenuView',
      component: MenuDetails,
      meta: { authorities: [Authority.USER] },
    },
    // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
  ],
};
