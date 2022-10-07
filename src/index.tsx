import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-ar' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo managed workflow\n';

const Ar = NativeModules.Ar
  ? NativeModules.Ar
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

export function multiply(a: number, b: number): Promise<number> {
  return Ar.multiply(a, b);
}

export function showToast(msg: string): void {
  return Ar.showToast(msg);
}

export function startARActivity(): void {
  return Ar.startARActivity();
}

export function startTrip(): void {
  return Ar.startTrip();
}

export function endTrip(): void {
  return Ar.endTrip();
}
