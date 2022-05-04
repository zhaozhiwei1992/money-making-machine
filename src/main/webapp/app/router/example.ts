import { Authority } from '@/shared/security/authority';

const UiExample = () => import('@/example/uiexample.vue');
const HelloWorld = () => import('@/example/hello-world.vue');

export default [
  {
    path: '/example/uiexample',
    name: 'DynamicUiExample',
    component: UiExample,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/example/helloworld',
    name: 'HelloWorld',
    component: HelloWorld,
    meta: { authorities: [Authority.USER] },
  },
];
