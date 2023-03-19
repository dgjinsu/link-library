import { atom } from 'recoil';

export const userState = atom({
  // 현재 로그인된 유저 정보
  key: 'userState',
  default: { username: '', password: '' },
});

export const usersState = atom({
  // temporary user DB
  key: 'usersState',
  default: JSON.parse(localStorage.getItem('usersState') ?? '[]'),
  effects_UNSTABLE: [
    ({ onSet }) => {
      onSet((newValue) => {
        localStorage.setItem('usersState', JSON.stringify(newValue));
      });
    },
  ],
});

export const saveUserToLocalStorage = (user) => {
  // 웹 키면 유저 데이터를  불러옴
  const users = JSON.parse(localStorage.getItem('usersState')) ?? [];
  localStorage.setItem('usersState', JSON.stringify([...users, user]));
};

export const isLoggedInState = atom({
  key: 'isLoggedInState',
  default: JSON.parse(localStorage.getItem('isLoggedIn')),
  effects_UNSTABLE: [
    ({ onSet }) => {
      onSet((newValue) => {
        localStorage.setItem('isLoggedIn', JSON.stringify(newValue));
      });
    },
    ({ onSet }) => {
      onSet((newValue, oldValue) => {
        if (oldValue !== undefined && newValue !== oldValue) {
          const storedUsers = JSON.parse(
            localStorage.getItem('usersState') ?? '[]'
          );
          onSet(usersState, storedUsers);
        }
      });
    },
  ],
});

export const isSidebarOpenState = atom({
  // 사이드바 열림/닫힘 상태
  key: 'isSidebarOpenState',
  default: false,
});

export const expandedCategoryState = atom({
  // 카테고리 열림/닫힘 상태
  key: 'expandedCategoryState',
  default: [],
});

export const isCreatingNewCategoryState = atom({
  key: 'isCreatingNewRootCategoryState',
  default: false,
});
